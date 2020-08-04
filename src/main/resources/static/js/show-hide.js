$(function () {
    $('.about-me').on('click', function () {
        console.log("click");
        if ($('#data').is(':visible')) {
            $('#data').hide(400);
        } else {
            $('#data').show(400);
        };

        if ( $('input.about-me').css('transform') == 'matrix(1, 0, 0, 1, 0, 0)'){
            $('input.about-me').css({
                transition: 'all 0.4s',
                transform: 'rotate(90deg)'
            });
        } else  {
            $('input.about-me').css({
                transition: 'all 0.4s',
                transform: 'rotate(0deg)'
            });
        };
    });

    $('.av-ankets').on('click', function () {
        if ($('#anket-list').is(':visible')) {
            $('#anket-list').hide(400);
        } else {
            $('#anket-list').show(400);
        };

        if ( $('input.av-ankets').css('transform') == 'matrix(1, 0, 0, 1, 0, 0)'){
            $('input.av-ankets').css({
                transition: 'all 0.4s',
                transform: 'rotate(90deg)'
            });
        } else  {
            $('input.av-ankets').css({
                transition: 'all 0.4s',
                transform: 'rotate(0deg)'
            });
        };
    });

    $('.fill-ankets').on('click', function () {
        if ($('#fill-anket-list').is(':visible')) {
            $('#fill-anket-list').hide(400);
        } else {
            $('#fill-anket-list').show(400);
        };

        if ( $('input.fill-ankets').css('transform') == 'matrix(1, 0, 0, 1, 0, 0)'){
            $('input.fill-ankets').css({
                transition: 'all 0.4s',
                transform: 'rotate(90deg)'
            });
        } else  {
            $('input.fill-ankets').css({
                transition: 'all 0.4s',
                transform: 'rotate(0deg)'
            });
        };
    });

    $('.patient-list').on('click', function () {
        if ($('#p-list').is(':visible')) {
            $('#p-list').hide(400);
        } else {
            $('#p-list').show(400);
        };

        if ( $('input.patient-list').css('transform') == 'matrix(1, 0, 0, 1, 0, 0)'){
            $('input.patient-list').css({
                transition: 'all 0.4s',
                transform: 'rotate(90deg)'
            });
        } else  {
            $('input.patient-list').css({
                transition: 'all 0.4s',
                transform: 'rotate(0deg)'
            });
        };
    });

    $('#checkAll').on('click', function(){
        if ( $('#checkAll').prop('checked')) {
            $('input').prop('checked', true);
        } else {
            $('input').prop('checked', false);
        }
        console.log('click');
    });
})