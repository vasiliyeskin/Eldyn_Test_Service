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

        var dropdown = $("#cource");
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