var ajaxUrl = "ajax/admin/advisers/";
var datatableApi;

defaulOrg = 0;
defaulPosition = 0;

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
                    return [row.lastname, row.firstname, row.middlename].join(" ");
                }
            },
            {
                "data": "organization.name"
            },
            {
                "data": "position.positionIO"
            },
            {
                "data": "email",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<a href='mailto:" + data + "'>" + data + "</a>";
                    }
                    return data;
                }
            },
            {
                "data": "phone"
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
                "orderable": false,
                "defaultContent": "",
                "data": null,
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<a onclick='updateRow(" + row.id + ");fillDropdownOrg(" + (data.organization.id - 1) + ");fillDropdownPosition(" + (data.position.id - 1) + ");'>" +
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
        },
        "initComplete": makeEditable
    });

    $.get("ajax/admin/position/", function (data) {
        var dropdown = $("#position");
        dropdown.find("option").remove();
        $.each(data, function (key, val) {
            dropdown.append($('<option></option>').attr('value', val.id).text(val.positionIO));
        });
    });

    $.get("ajax/admin/organizations/", function (data) {
        var dropdown = $("#org");
        dropdown.find("option").remove();
        $.each(data, function (key, val) {
            dropdown.append($('<option></option>').attr('value', val.id).text(val.name));
        });
    });
});

function fillDropdownOrg(id) {

    var dropdown = $("#org");
    dropdown.prop('selectedIndex', id);
}

function fillDropdownPosition(id) {
    var dropdown = $("#position");
    dropdown.prop('selectedIndex', id);
}

function fillFullName() {
    var first = $("#firstname");
    var middle = $("#middlename");
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
    $("#org").prop('selectedIndex', defaulOrg);
    $("#position").prop('selectedIndex', defaulPosition);
}

function saveDefault(){
    defaulOrg = $("#org").val() - 1;
    defaulPosition = $("#position").val() - 1;
}
