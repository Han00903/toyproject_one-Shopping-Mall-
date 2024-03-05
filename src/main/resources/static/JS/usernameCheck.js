function usernameCheck() {
    const username = $("#user_id").val();
    if (username == "") {
        alert("아이디를 입력해주세요!. 필수항목입니다.");
        $("#user_id").focus();
        return false;
    }
    $.ajax({
        type: "get",
        url: "/api/overlap/usernameRegister",
        data: {"user_id": user_id},
        dataType: "JSON",

        success: function (result) {
            if (result.result == "0") {
                if (confirm("이 아이디는 사용 가능합니다. \n사용하시겠습니까?")) {
                    usernameOverlapCheck = 1;
                    $("#user_id").attr("readonly", true);
                    $("#user_idOverlay").attr("disabled", true);
                    $("#user_idOverlay").css("display", "none");
                    $("#resetUser_id").attr("disabled", false);
                    $("#resetUser_id").css("display", "inline-block");
                }
                return false;
            } else if (result.result == "1") {
                alert("이미 사용중인 아이디입니다.");
                $("#user_id").focus();
            } else {
                alert("success이지만 result 값이 undefined 잘못됨");
            }
        },
        error: function (request, status,error) {
            alert("ajax 실행 실패");
            alert("code:" + request.status + "\n" + "error :" + error);
        }
    });

}