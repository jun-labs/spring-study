package project.study.waiting.test.common.redis

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.test.context.ActiveProfiles
import org.springframework.util.StringUtils
import redis.embedded.RedisServer
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

@Configuration
@ActiveProfiles("test")
class EmbeddedRedisConfiguration {

    @Value("\${spring.redis.port}")
    private var port: Int = 0

    @Value("\${spring.redis.host}")
    private lateinit var host: String

    private var redisServer: RedisServer? = null

    @Bean
    fun embeddedRedisConnectionFactory(): RedisConnectionFactory {
        val redisStandaloneConfiguration = RedisStandaloneConfiguration()
        redisStandaloneConfiguration.hostName = host
        redisStandaloneConfiguration.port = port
        return LettuceConnectionFactory(redisStandaloneConfiguration)
    }

    @Bean(name = ["embeddedRedisTemplate"])
    fun embeddedRedisTemplate(): RedisTemplate<String, Any> {
        val embeddedRedisTemplate = RedisTemplate<String, Any>()
        embeddedRedisTemplate.setConnectionFactory(embeddedRedisConnectionFactory())
        embeddedRedisTemplate.keySerializer = StringRedisSerializer()
        embeddedRedisTemplate.valueSerializer = StringRedisSerializer()
        return embeddedRedisTemplate
    }

    @PostConstruct
    fun redisServer() {
        redisServer = RedisServer(if (isRunning()) findAvailablePort() else port)
        redisServer?.start()
    }

    @PreDestroy
    fun stopRedis() {
        redisServer?.stop()
    }

    @Throws(IOException::class)
    private fun isRunning(): Boolean {
        return isRunning(executeGrepProcessCommand(port))
    }

    @Throws(IOException::class)
    private fun findAvailablePort(): Int {
        for (port in 10000..65535) {
            val process = executeGrepProcessCommand(port)
            if (!isRunning(process)) {
                return port
            }
        }
        throw IllegalArgumentException("Not Found Available port: 10000 ~ 65535")
    }

    private fun isRunning(process: Process): Boolean {
        var line: String?
        val pidInfo = StringBuilder()

        try {
            BufferedReader(InputStreamReader(process.inputStream)).use { input ->
                while (input.readLine().also { line = it } != null) {
                    pidInfo.append(line)
                }
            }
        } catch (e: Exception) {
        }

        return StringUtils.hasText(pidInfo.toString())
    }

    private fun executeGrepProcessCommand(port: Int): Process {
        val command = "netstat -nat | grep LISTEN|grep $port"
        val shell = arrayOf("/bin/sh", "-c", command)
        return Runtime.getRuntime().exec(shell)
    }
}
