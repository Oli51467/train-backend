const UserModule = {
    state: {
        id: "",
        mobile: "",
        token: "",
        is_login: false,
    },
    getters: {
    },
    // 在mutations里更新state的状态 在actions里调用, 在mutations里不能执行异步操作
    mutations: {
        updateUser(state, user) {
            state.id = user.id,
            state.mobile = user.mobile,
            state.is_login = true
        },
    },
    modules: {
    }
}

export default UserModule;
