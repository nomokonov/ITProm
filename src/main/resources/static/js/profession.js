$(document).ready(function () {
    $('#ProfessionModal').on('hidden.bs.modal', function (e) {
        let form = $('#ProfessionModal').find('form');
        form.attr('action','/profession/add');
        form.find('#professionName').val('');
        form.find('#professionNotice').val('');
        form.find('#professionId').remove();
        form.find('#ProfessionModalTitle').text('Новая должность');
    });
})

function deleteProfessionClick(item_id) {
    confirmDialog('Подтвердите удаление записи', function(){
        $.ajax({
            method: "POST",
            dataType: "html",
            url: "/profession/delete",
            data: { professionId: item_id }
        })
            .done(function( msg ) {
                location.reload();
            });
    });

}

function editProfessionClick(element,item_id) {
    let form = $('#ProfessionModal').find('form');
    form.attr('action','/profession/edit');
    form.find('#professionName').val(element.getAttribute('data-professionName'));
    form.find('#professionNotice').val(element.getAttribute('data-professionNotice'));
    form.find('#ProfessionModalTitle').text('Редактирование должности');

    $('<input>').attr({
        type: 'hidden',
        id: 'professionId',
        name: 'professionId',
        value:item_id
    }).appendTo(form);
    $('#ProfessionModal').modal('show');
}


function confirmDialog(message, onConfirm){
    var fClose = function(){
        modal.modal("hide");
    };
    var modal = $("#confirmModal");
    modal.modal("show");
    $("#confirmMessage").empty().append(message);
    $("#confirmOk").one('click', onConfirm);
    $("#confirmOk").one('click', fClose);
    $("#confirmCancel").one("click", fClose);
}
