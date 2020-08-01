$(function () {
    $('input:radio[name=roleName]').on('change', function () {
        if ($('input:radio[name=roleName]:checked').val() === 'ROLE_PATIENT')
        {
            $("#date").show();
        } else {
            $("#date").hide();
        };
    });
})