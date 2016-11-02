package tekdays

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Secured('ROLE_ADMIN')
@Transactional(readOnly = true)
class TekEventController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def taskService

    @Secured('ROLE_USER')
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TekEvent.list(params), model: [tekEventCount: TekEvent.count()]
    }

    def show(TekEvent tekEvent) {

        if (tekEvent == null) {
            flash.message = "TekEvent not found with id ${params.id}"
            redirect(action: "index")
            return
        }
        respond tekEvent

    }

    def create() {
        respond new TekEvent(params)
    }

    @Transactional
    def save(TekEvent tekEvent) {
        if (tekEvent == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tekEvent.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tekEvent.errors, view: 'create'
            return
        }

        taskService.addDefaultTasks(tekEvent)
        tekEvent.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tekEvent.label', default: 'TekEvent'), tekEvent.id])
                redirect tekEvent
            }
            '*' { respond tekEvent, [status: CREATED] }
        }
    }

    def edit(TekEvent tekEvent) {
        respond tekEvent
    }

    @Transactional
    def update(TekEvent tekEvent) {
        if (tekEvent == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tekEvent.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tekEvent.errors, view: 'edit'
            return
        }

        tekEvent.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tekEvent.label', default: 'TekEvent'), tekEvent.id])
                redirect tekEvent
            }
            '*' { respond tekEvent, [status: OK] }
        }
    }

    @Transactional
    def delete(TekEvent tekEvent) {

        if (tekEvent == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        tekEvent.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tekEvent.label', default: 'TekEvent'), tekEvent.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tekEvent.label', default: 'TekEvent'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
