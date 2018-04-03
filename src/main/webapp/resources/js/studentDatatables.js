var ajaxUrl = "ajax/admin/students/";
var datatableApi;

defaulTraining = 0;
defaulCourse = 0;
defaulAdviser = 0;

function updateTable() {
    $.get(ajaxUrl, updateTableByData);
}

function enable(chkbox, id) {
    var enabled = chkbox.is(":checked");
//  https://stackoverflow.com/a/22213543/548473
    $.ajax({
        url: ajaxUrl + id,
        type: "POST",
        data: "enabled=" + enabled
    }).done(function () {
        chkbox.closest("tr").toggleClass("disabled");
        successNoty(enabled ? "common.enabled" : "common.disabled");
    }).fail(function () {
        $(chkbox).prop("checked", !enabled);
    });
}

//$(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "ajax": {
            "url": ajaxUrl,
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
                "data": "data",
                "render": function (data, type, row) {
                    if(row.adviser!=null)
                        return [row.adviser.lastname, row.adviser.firstname, row.adviser.middlename].join(" ");
                    else
                        return "";
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
            {
                "data": "active",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<input type='checkbox' " + (data ? "checked" : "") + " onclick='enable($(this)," + row.id + ");'/>";
                    }
                    return data;
                }
            },
            {
                "orderable": false,
                "defaultContent": "",
                "data": null,
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<a onclick='updateRow(" + row.id + ");chooseDropdownAdviser(" + ((data.adviser!=null)?(data.adviser.id - 1):0) + ");chooseDropdownTD(" + (data.trainingDirection.id - 1) + ");'>" +
                            "<span class='fa fa-pencil' aria-hidden='true'></span></a>";
                    }
                }
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
            }
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
    });

    // fill dropdown Adviser
    $.get("ajax/admin/advisers/", function (data) {
        var dropdown = $("#adviser");
        dropdown.find("option").remove();
        $.each(data, function (key, val) {
            dropdown.append($('<option></option>').attr('value', val.id).text([val.lastname, val.firstname, val.middlename].join(" ")));
        });
    });

    // fill dropdown training direction
    $.get("ajax/admin/td/", function (data) {
        var dropdown = $("#trainingDirection");
        dropdown.find("option").remove();
        $.each(data, function (key, val) {
            dropdown.append($('<option></option>').attr('value', val.id).text(val.shortname));
        });
    });
});

function chooseDropdownAdviser(id) {
    $("#adviser").prop('selectedIndex', id);
}

function chooseDropdownTD(id) {
    $("#trainingDirection").prop('selectedIndex', id);
}

function fillFullName() {
    var first = $("#firstname");
    var middle = $("#midlename");
    var last = $("#lastname");

    var fio = last.val().split(" ");
    last.val(fio[0]);
    if (middle.val().length == 0) {
        middle.val(fio[2]);
    }
    if (first.val().length == 0) {
        first.val(fio[1]);
    }
}

function chooseDefault()
{
    $("#trainingDirection").prop('selectedIndex', defaulTraining);
    $("#adviser").prop('selectedIndex', defaulAdviser);
    $("#course").val(defaulCourse);
}

function saveDefault(){
    defaulTraining = $("#trainingDirection").val() - 1;
    defaulAdviser = $("#adviser").val() - 1;
    defaulCourse = $("#course").val();
}