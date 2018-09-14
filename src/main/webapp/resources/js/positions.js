var ajaxUrl = "ajax/admin/position/";
var datatableApi;

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
                "data": "positionIO"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
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
