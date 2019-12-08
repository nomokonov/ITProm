$(document).ready(function () {
    $('#DepartmentModal').on('hidden.bs.modal', function (e) {
        let form = $('#DepartmentModal').find('form');
        form.attr('action','/department/add');
        form.find('#departmentName').val('');
        form.find('#departmentNotice').val('');
        form.find('#departmentParent').val('');
        form.find('#departmentId').remove();
        form.find('#DepartmentModalTitle').text('Новый отдел');
    });
})

function deleteDepartmentClick(item_id) {
    confirmDialog('Подтвердите удаление записи', function(){
        $.ajax({
            method: "POST",
            dataType: "html",
            url: "/department/delete",
            data: { departmentId: item_id }
        })
            .done(function( msg ) {
                location.reload();
            });
    });

}

function editDepartmentClick(element,item_id) {
    let form = $('#DepartmentModal').find('form');
    form.attr('action','/department/edit');
    form.find('#departmentName').val(element.getAttribute('data-departmentName'));
    form.find('#departmentNotice').val(element.getAttribute('data-departmentNotice'));
    form.find('#departmentParent').val(element.getAttribute('data-departmentParent'));
    form.find('#DepartmentModalTitle').text('Редактирование должности');

    $('<input>').attr({
        type: 'hidden',
        id: 'departmentId',
        name: 'departmentId',
        value:item_id
    }).appendTo(form);
    $('#DepartmentModal').modal('show');
}

