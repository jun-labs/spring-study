package project.swagger.singlemodule.test.unittest

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldNotBe
import project.swagger.singlemodule.core.domain.entity.User

internal class UserUnitTest : DescribeSpec({

    describe("[UnitTest] User Create Test") {
        context("When: parameters are valid") {
            it("Then: can create user.") {
                User(1, "John") shouldNotBe null
            }
        }
    }

    describe("[UnitTest] Object Extends Method Test") {
        context("When: override equals and hashcode") {
            it("Then: compare to object with value.") {
                User(1, "John") shouldBeEqualToComparingFields User(1, "John")
            }
        }

        context("When: override toString") {
            it("Then: should be output as defined.") {
                "userId: 1" == User(1, "John").toString()
            }
        }
    }
})
