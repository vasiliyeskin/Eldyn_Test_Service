var form;

function makeEditable() {
    form = $('#detailsForm');
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(jqXHR);
    });

    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});
}

// https://api.jquery.com/jquery.extend/#jQuery-extend-deep-target-object1-objectN
function extendsOpts(opts) {
    $.extend(true, opts,
        {
            "ajax": {
                "url": ajaxUrl,
                "dataSrc": ""
            },
            "paging": false,
            "info": true,
            "language": {
                "search": i18n["common.search"]
            },
            "initComplete": makeEditable
        }
    );
    return opts;
}

function add() {
    $("#modalTitle").html(i18n["addTitle"]);
    form.find(":input").val("");
    $("#editRow").modal();
}

function updateRow(id) {
    $("#modalTitle").html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('#editRow').modal();
    });
}

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: "DELETE"
    }).done(function () {
        updateTable();
        successNoty("common.deleted");
    });
}

function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}


function enable(chkbox, id) {
    var enabled = chkbox.is(":checked");
//  https://stackoverflow.com/a/22213543/548473
    $.ajax({
        url: ajaxUrl + id,
        type: "POST",
        data: "active=" + enabled
    }).done(function () {
        chkbox.closest("tr").toggleClass("disabled");
        successNoty(enabled ? "Enabled" : "Disabled");
    }).fail(function () {
        $(chkbox).prop("checked", !enabled);
    });
}

function save() {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize()
    }).done(function () {
        $("#editRow").modal("hide");
        updateTable();
            successNoty("common.saved");
    }
    );
}

var failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(key) {
    closeNoty();
    new Noty({
        text: "<span class='fa fa-ok'></span> &nbsp;" + i18n[key],
        type: 'success',
        layout: "bottomRight",
        timeout: 1000
    }).show();
}

function failNoty(jqXHR) {
    closeNoty();
    failedNote = new Noty({
        text: "<span class='fa fa-exclamation-sign'></span> &nbsp;" + i18n["common.errorStatus"] + ": " + jqXHR.status + (jqXHR.responseJSON ? "<br>" + jqXHR.responseJSON : ""),
        type: "error",
        layout: "bottomRight"
    }).show();
}

function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='updateRow(" + row.id + ");'>" +
            "<span class='fa fa-pencil' aria-hidden='true'></span></a>";
    }
}

function renderDeleteBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='deleteRow(" + row.id + ");'>" +
            "<span class='fa fa-remove' aria-hidden='true'></span></a>";
    }
}