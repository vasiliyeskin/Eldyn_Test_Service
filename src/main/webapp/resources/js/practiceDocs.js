//$(document).ready(function () {
$(function () {

    // fill dropdown training direction
    $.get("ajax/admin/td/", function (data) {
        var dropdown = $("#trainingDirection");
        dropdown.find("option").remove();
        $.each(data, function (key, val) {
            dropdown.append($('<option></option>').attr('value', val.id).text(val.shortname));
        });
    });

    var dropdown = $("#course");
    dropdown.find("option").remove();
    dropdown.append($('<option></option>')
        .attr('value', 1).text("1"))
        .append($('<option></option>')
            .attr('value', 2).text("2"))
        .append($('<option></option>')
            .attr('value', 3).text("3"))
        .append($('<option></option>')
            .attr('value', 4).text("4"))
        .append($('<option></option>')
            .attr('value', 5).text("5"));

    // fill dropdown curator
    $.get("ajax/admin/curator/", function (data) {
        var dropdown = $("#curator");
        dropdown.find("option").remove();
        $.each(data, function (key, val) {
            dropdown.append($('<option></option>').attr('value', val.id).text([val.lastname, val.firstname, val.middlename].join(" ")));
        });
    });

    // fill dropdown practice
    $.get("ajax/admin/practice/", function (data) {
        var dropdown = $("#practice");
        dropdown.find("option").remove();
        $.each(data, function (key, val) {
            dropdown.append($('<option></option>').attr('value', val.id).text(val.name));
        });
    });
});

function selectPractice() {
    var dropdown = $("#practice");
    var endDate = $("#end");
    var startDate = $("#start");
    endDate.val(dropdown.val().enddate);

    $.get("ajax/admin/practice/" + dropdown.val(), function (data) {
        startDate.val(data.startDate.substr(0, 10));
        endDate.val(data.endDate.substr(0, 10));
    });
    //   dropdown.options[dropdown.selectedIndex].value
}

function getDocs() {
    $('#result').html("Sending ...");

    var data =
        "practice=" + $("#practice").val() +
        "&curator=" + $("#curator").val() +
        "&trainingDirection=" + $("#trainingDirection").val() +
        "&course=" + $("#course").val();

    //        https://stackoverflow.com/a/22213543/548473
    $.ajax({
        url: "ajax/admin/curator/getDocsForCurator/",
        data: data,
        type: "POST",
        contentType: "application/x-www-form-urlencoded",
        processData: false
    }).done(function (result) {
        if (typeof result === "object") {
            result = JSON.stringify(result)
        }
        $('#result').html(result);

        $('#downloadFrame').remove();
        $('body').append('<iframe id="downloadFrame" style="display:none"></iframe>');
        $('#downloadFrame').attr('src',['ajax/admin/curator/download/',result].join(""));
    }).fail(function (result) {
        $('#result').html(result.responseText);
    });
}
