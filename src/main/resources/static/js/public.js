
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

// set active menu item
function setActiveTab() {
    var bars = document.getElementById('nav-bar-list'), i, j, k;

    if (!bars) return;

    var link, linkChild, linkParent;
    var curPathName = window.location.pathname;

    // Берём все табы по навигации и ищем текущий
    for (i = 0; i < bars.childNodes.length; i++) {
        if (bars.childNodes[i].nodeName === 'LI') {
            // Ищем среди дочерних
            for (j = 0; j < bars.childNodes[i].childNodes.length; j++) {
                link = bars.childNodes[i].childNodes[j];

                //Если глубина дочернего элемента не больше 1
                if (bars.childNodes[i].childElementCount === 1 && link.nodeName === 'A' && link.pathname === curPathName) {
                    link.parentElement.classList.add('active');
                    // Отписываемся от события
                    document.removeEventListener('DOMContentLoaded', setActiveTab);

                    return;
                }

                //Запоминаем позицию верхнего элемента (заголовка) выпадающего списка
                if (bars.childNodes[i].childElementCount > 1 && link.nodeName === 'A' && link.pathname === curPathName) {
                    linkParent = bars.childNodes[i].childNodes[j];
                }
                //Находим текущую позицию открытой вкладки
                if (link.nodeName === 'UL') {
                    for (k = 0; k < bars.childNodes[i].childNodes[j].childNodes.length; k++) {
                        link = bars.childNodes[i].childNodes[j].childNodes[k];

                        if (link.nodeName === 'LI' && link.childNodes[0].pathname === curPathName) {
                            //применяем шаблоны css к заголовку выпадающего списка
                            linkParent.parentElement.classList.add('active');
                            // Отписываемся от события
                            document.removeEventListener('DOMContentLoaded', setActiveTab);
                            return;
                        }
                    }
                }
            }
        }
    }
}

document.addEventListener('DOMContentLoaded', setActiveTab);