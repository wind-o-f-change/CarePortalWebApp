$(function () {

    $('form.pass-header').on('submit', function (e) {

        if ($('#pass').val() != $('#passConfirm').val()) {
            e.preventDefault();
            $('#error-pass').show();
        }
    });

    $('input#oldpass').on('focus', function () {
        $('.message-section').css('display', 'none');
    })
})