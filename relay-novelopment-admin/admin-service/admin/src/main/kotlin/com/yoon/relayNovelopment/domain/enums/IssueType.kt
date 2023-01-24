package com.yoon.relayNovelopment.domain.enums

enum class IssueType {
    BUG, TASK, APPROVAL_RELAY;

    companion object {
        // 이 방법도 있지만,
        fun of(type : String) = valueOf(type.uppercase())

        operator fun invoke(type:String) = valueOf(type.uppercase())
    }

}

fun main() {

    val bug1 = IssueType.of("bug")
    val bug = IssueType.BUG
    if(bug1 == bug){
        println("true")
    }

    val issueType = IssueType("bug") // operator 로 함수명 없이 생성자처럼 사용가능하다
    if(bug1 == issueType){
        println("true true")
    }
}