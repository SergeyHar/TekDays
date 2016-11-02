package tekdays

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import org.springframework.security.access.prepost.PreAuthorize

import static org.springframework.http.HttpStatus.*

@Secured('ROLE_ADMIN')
@Transactional(readOnly = true)
class TekUserController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
//
//    def index(Integer max) {
//        params.max = Math.min(max ?: 10, 100)
//        respond TekUser.list(params), model:[tekUserCount: TekUser.count()]
//    }

    @Secured (['ROLE_USER', 'ROLE_ADMIN'])
    def index() {
        redirect(action: "list", params: params)
    }


    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TekUser.list(params), model: [tekUserCount: TekUser.count()]
    }

    def show(TekUser tekUser) {
        respond tekUser
    }

    def create() {
        respond new TekUser(params)
    }

    @Transactional
    def save(TekUser tekUser) {
        if (tekUser == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tekUser.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tekUser.errors, view: 'create'
            return
        }

        tekUser.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tekUser.label', default: 'TekUser'), tekUser.id])
                redirect tekUser
            }
            '*' { respond tekUser, [status: CREATED] }
        }
    }

    def edit(TekUser tekUser) {
        respond tekUser
    }

    @Transactional
    def update(TekUser tekUser) {
        if (tekUser == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tekUser.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tekUser.errors, view: 'edit'
            return
        }

        tekUser.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tekUser.label', default: 'TekUser'), tekUser.id])
                redirect tekUser
            }
            '*' { respond tekUser, [status: OK] }
        }
    }

    @Transactional
    def delete(TekUser tekUser) {

        if (tekUser == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        tekUser.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tekUser.label', default: 'TekUser'), tekUser.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tekUser.label', default: 'TekUser'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    def login = {
//        if (request.get) {
//            return // render the login view
//        }
//
//        def u = TekUser.findByUserName(params.username)
//        if (u) {
//            if (u.password == params.password) {
//                session.user = u
//                redirect(action: "list")
//            }
//            else {
                            flash.message = "Password incorrect."
////            render(view: 'login')
//
//                render(view: "login", model: [message: "Password incorrect"])
//            }
//        }
//        else {
//            flash.message = "User not found."
//
//
//            render(view: "login", model: [message: "User not found"])
//        }

    }

    def logout = {
        session.user = null
        redirect(url: resource(dir: ''))
    }

    def validate = {
//        def user = TekUser.findByUserName(params.username)
//        if (user && user.password == params.password) {
//            session.user = user
//            if (params.cName)
//                redirect(controller: params.cName, action: params.aName)
//            else
//                redirect(controller: 'tekEvent', action: 'index')
//        } else {
//            flash.message = "Invalid username and password."
//            render(view: 'login')
//        }
//

        //



    }
}
