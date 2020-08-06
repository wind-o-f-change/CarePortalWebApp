$(function () {
    $('#update-data').on('click', function () {
       $('#data').hide();
       $('#update-form').show();
    });

    $('#reset').on('click', function () {
        $('#data').show();
        $('#update-form').hide();
    });
})