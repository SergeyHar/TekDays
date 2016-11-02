package tekdays


class SecurityInterceptor {

    SecurityInterceptor() {
        matchAll()
                .except(controller:'TekUserController', action:'login')
    }

    boolean before() {

        if (!session.user && actionName != "login") {
            redirect(controller: "TekUser", action: "login")
            return false
        }
        return true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
