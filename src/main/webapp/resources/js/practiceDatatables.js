var ajaxUrl = "ajax/admin/practice/";
var datatableApi;

defaulTraining = 0;
defaulCourse = 0;
defaulAdviser = 0;
defaulCurator = 0;

function updateTable() {
    $.get(ajaxUrl, updateTableByData);
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
                "data": "name"
            },
            {
                "data": "nameDirection"
            },
            {
                "data": "nameRod"
            },
            {
                "data": "startDate",
                "render": function (date, type, row) {
                    if (type === "display") {
                        return date.substring(0, 10);
                    }
                    return date;
                }
            },
            {
                "data": "endDate",
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
                        return "<a onclick='updateRowTX(" + row.id + ");'>" +
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


    $.datetimepicker.setLocale(localeCode);

//  http://xdsoft.net/jqplugins/datetimepicker/
    var startDate = $('#startDate');
    var endDate = $('#endDate');
    startDate.datetimepicker({
        timepicker: false,
        format: 'Y-m-d',
        formatDate: 'Y-m-d',
        onShow: function (ct) {
            this.setOptions({
                maxDate: endDate.val() ? endDate.val() : false
            })
        }
    });
    endDate.datetimepicker({
        timepicker: false,
        format: 'Y-m-d',
        formatDate: 'Y-m-d',
        onShow: function (ct) {
            this.setOptions({
                minDate: startDate.val() ? startDate.val() : false
            })
        }
    });

    $('#startDate').datetimepicker({
        format: 'Y-m-d'
    });

    $('#endDate').datetimepicker({
        format: 'Y-m-d'
    });

});

function updateRowTX(id) {
    $("#modalTitle").html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("textarea[name='" + key + "']").val(value);
        });
        $('#editRow').modal();
    });

    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);

            if(key=='startDate' || key=='endDate') {
                form.find("input[name='" + key + "']").val(value.substr(0,10));
            }

        });
        $('#editRow').modal();
    });
}
