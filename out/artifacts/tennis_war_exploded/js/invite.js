/**
 * 选择是否同意服务器协议
 * @param {object} element 被点击的元素
 */
function selectTreaty(element) {
    if (element.className == 'flex-box-0 invite-reg-false') {
        element.className = 'flex-box-0 invite-reg-true'; //选择服务协议
    } else {
        element.className = 'flex-box-0 invite-reg-false'; //没有选择服务协议
    }
}

/**
 * 选择注册的用户类型
 * @param {object} element 被点击的元素
 */
function selectUserType(element,id) {
    var all = element.parentNode.querySelectorAll('.flex-box-1');
    for (var i = 0; i < all.length; i++) {
        all[i].className = 'flex-box-1';
    }
    element.className = 'flex-box-1 on';
    document.getElementById('type').value = id;
}