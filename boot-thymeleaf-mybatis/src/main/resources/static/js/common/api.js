let {body} = document;
let WIDTH = 1024;
let RATIO = 3;

//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

//前端API访问接口
let api = {
    user: {
        list(pageCode, pageSize) {
            return '/user/list?pageCode=' + pageCode + '&pageSize=' + pageSize;
        },
        getUser(id) {
            return "/user/findById?id=" + id;
        },
        roleList: '/role/list',
        add: '/user/add',
        update: '/user/update',
        delete: '/user/delete',
        checkName(name, id) {
            return "/user/checkName?name=" + name + '&id=' + id;
        }
    }
}