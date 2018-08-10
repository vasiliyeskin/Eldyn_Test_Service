//$(document).ready(function () {
$(function () {

    // fill dropdown training direction
    $.get("ajax/admin/td/", function (data) {
        var dropdown = $("#trainingDirection");
        dropdown.find("option").remove();
        $.each(data, function (key, val) {
            dropdown.append($('<option></option>').attr('value', val.id).text(val.shortname));
        });
        dropdown.selectedIndex = '0';
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
    dropdown.selectedIndex = 0;

    // fill dropdown curator
    $.get("ajax/admin/curator/", function (data) {
        var dropdown = $("#curator");
        dropdown.find("option").remove();
        $.each(data, function (key, val) {
            dropdown.append($('<option></option>').attr('value', val.id).text([val.lastname, val.firstname, val.middlename].join(" ")));
        });
        dropdown.selectedIndex = 0;
    });

    // fill dropdown practice
    $.get("ajax/admin/practice/", function (data) {
        var dropdown = $("#practice");
        dropdown.find("option").remove();
        $.each(data, function (key, val) {
            dropdown.append($('<option></option>').attr('value', val.id).text(val.name));
        });
    });

    updateStudentsListByString("curatorId=0&trainingDirectionID&course=1");
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




function updateStudentsList()
{
    var data =
        "curatorId=" + $("#curator").val() +
        "&trainingDirectionID=" + $("#trainingDirection").val() +
        "&course=" + $("#course").val();

    updateStudentsListByString(data);
}

function updateStudentsListByString(data)
{
    var resutlts = "";

    //        https://stackoverflow.com/a/22213543/548473
    $.ajax({
        url: "ajax/admin/practice/studentsByCuratorIdCourseAndTD/",
        data: data,
        type: "POST",
        contentType: "application/x-www-form-urlencoded",
        processData: false
    }).done(function (result) {
        if (typeof result === "object") {
            resutlts = result;
            result = JSON.stringify(result);
        }
        $('#result').html(result);
    }).fail(function (result) {
        $('#result').html(result.responseText);
    });


    var table = $('#datatable').DataTable();
    table.destroy();
/*
    //fill table
    datatableApi = $("#datatable").DataTable({
        "ajax": {
            "url": "ajax/admin/practice/studentsByCuratorIdCourseAndTD/",
            "type": "POST",
            "dataType": 'json',
            "data": data,
            "dataSrc": ""
        },
        "paging": true,
        "info": true,
        "columns": [
            {
                "data": "data",
                "render": function (data, type, row) {
                    return [row.lastname, row.firstname, row.midlename].join(" ");
                }
            },
            {
                "data": "course"
            },
            {
                "data": "trainingDirection.shortname"
            },
            {
                "data": "registered",
                "render": function (date, type, row) {
                    if (type === "display") {
                        return date.substring(0, 10);
                    }
                    return date;
                }
            },
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            if (!data.active) {
                $(row).addClass("disabled");
            }
        },
        "initComplete": makeEditable
    });*/
}
