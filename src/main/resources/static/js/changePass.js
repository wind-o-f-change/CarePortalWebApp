$(function () {

    $('form.pass-header').on('submit', function (e) {

        if ($('#pass').val() != $('#passConfirm').val()) {
            e.preventDefault();
            $('#error-pass').show();
        }
    });
})