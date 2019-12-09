$(document).ready(function () {
    $('#EmployeeModal').on('hidden.bs.modal', function (e) {
        let form = $('#EmployeeModal').find('form');
        form.attr('action','/employee/add');
        form.find('#employeeName').val('');
        form.find('#employeeNotice').val('');
        form.find('#employeeDepartment').val('');
        form.find('#employeeProfession').val('');
        form.find('#employeeId').remove();
        form.find('#EmployeeModalTitle').text('Новый сотрудник');
    });
})

function deleteEmployeeClick(item_id) {
    confirmDialog('Подтвердите удаление Сотрудника', function(){
        $.ajax({
            method: "POST",
            dataType: "html",
            url: "/employee/delete",
            data: { employeeId: item_id }
        })
            .done(function( msg ) {
                location.reload();
            });
    });

}

function editEmployeeClick(element,item_id) {
    let form = $('#EmployeeModal').find('form');
    form.attr('action','/employee/edit');
    form.find('#employeeName').val(element.getAttribute('data-employeeName'));
    form.find('#employeeNotice').val(element.getAttribute('data-employeeNotice'));
    form.find('#employeeDepartment').val(element.getAttribute('data-employeeDepartment'));
    form.find('#employeeProfession').val(element.getAttribute('data-employeeProfession'));
    form.find('#EmployeeModalTitle').text('Редактирование сотрудника');

    $('<input>').attr({
        type: 'hidden',
        id: 'employeeId',
        name: 'employeeId',
        value:item_id
    }).appendTo(form);
    $('#EmployeeModal').modal('show');
}

