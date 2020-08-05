$(function () {
    $('input:radio[name=roleName]').on('change', function () {
        if ($('input:radio[name=roleName]:checked').val() === 'ROLE_PATIENT')
        {
            $("#date").show();
            $('input[type=date]').prop('required', true);
        } else {
            $("#date").hide();
            $('input[type=date]').prop('required', false);
        };
    });
})