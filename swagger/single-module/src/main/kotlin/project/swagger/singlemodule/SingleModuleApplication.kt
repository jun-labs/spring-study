package project.swagger.singlemodule

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SingleModuleApplication

fun main(args: Array<String>) {
	runApplication<SingleModuleApplication>(*args)
}
