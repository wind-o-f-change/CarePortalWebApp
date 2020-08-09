$(function () {
    $('input:radio[name=roleName]').on('change', function () {
        if ($('input:radio[name=roleName]:checked').val() === 'ROLE_PATIENT')
        {
            $("#date").css('display','flex');
            $("#date").css('display','flex');
            $('input[type=date]').prop('required', true);
        } else {
            $("#date").hide();
            $('input[type=date]').prop('required', false);
        };
    });
})