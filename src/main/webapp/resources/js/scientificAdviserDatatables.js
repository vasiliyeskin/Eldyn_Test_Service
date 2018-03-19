var ajaxUrl = "ajax/admin/advisers/";
var datatableApi;

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
                "data": "id"
            },
            {
                "data": "firstname"
            },
            {
                "data": "middlename"
            },
            {
                "data": "lastname"
            },
            {
                "data": "organization.name"
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
                "data": "organization.id",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<a onclick='updateRow(" + row.id + ");fillDropdownOrg("+(data-1)+");'>" +
                            "<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></a>";
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
});

function fillDropdownOrg(id) {

    $.get("ajax/admin/organizations/", function (data) {
        var dropdown = $("#org");
        dropdown.find("option").remove();
        $.each(data, function (key, val) {
            dropdown.append($('<option></option>').attr('value', val.id).text(val.name));
        });
        dropdown.prop('selectedIndex', id);
    });
}
