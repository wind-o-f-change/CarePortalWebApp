$(function () {
    $('.hidden-checkbox').on('click', function () {
        $(this).find(":checkbox").prop('checked', false);
        $(this).parent('tr').css('display', 'none');
    });
    $('#pat-reset').on('click', function () {
        $('tr').css('display', 'table-row');
    })
})