function setCookie(name, value, days) {
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        var expires = "; expires=" + date.toGMTString();
    }
    else var expires = "";
    document.cookie = name + "=" + value + expires + "; path=/";
}
function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
    }
    return null;
}
function eraseCookie(name) {
    setCookie(name, "", -1);
};

$(document).ready(function() {
    $('select[name="find_action"]').change(function() {
        if(getCookie('list') != '0') {
            setCookie('list', $(this).val(), 1);
        }
    });
    if(getCookie('list')) {
        $('select[name="find_action"]').val(getCookie('list')).find('option[value="' + getCookie('list') + '"]').attr('selected', 'selected');
    }
});
