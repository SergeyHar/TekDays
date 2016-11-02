package tekdays

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Secured('ROLE_ADMIN')
@Transactional(readOnly = true)
class MessageController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def list
        def count
        def event = TekEvent.get(params.id)
        if (event) {
            list = Message.findAllByEvent(event, params)
            count = Message.countByEvent(event)
        } else {
            list = Message.list(params)
            count = Message.count()
        }

        render(view:'ajaxList' ,
                model:[messageInstanceList: list, messageInstanceTotal: count,
                       event: event])
//        respond Message.list, model:[messageCount: Message.count()]
//        respond list, model: [messageCount: count]

    }
    def showDetail = {
        def messageInstance = Message.get(params.id)
        if (messageInstance) {
            render(template:"details" , model:[messageInstance: messageInstance])

        }
        else {
            render "No message found with id: ${params.id}"
        }
    }

    def reply = {
        def parent = Message.get(params.id)
        def messageInstance = new Message(parent:parent, event:parent.event,
                subject:"RE: $parent.subject" )
        render(view:'create' , model:['messageInstance' :messageInstance])
    }


    def show(Message messageObj) {
        respond messageObj
    }

    def create() {
        respond new Message(params)
    }

    @Transactional
    def save(Message messageObj) {
        if (messageObj == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (messageObj.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond message.errors, view: 'create'
            return
        }

        messageObj.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'message.label', default: 'Message'), messageObj.id])
                redirect messageObj
            }
            '*' { respond messageObj, [status: CREATED] }

        }

    }

    def edit(Message messageObj) {
        respond messageObj
    }

    @Transactional
    def update(Message messageObj) {
        if (messageObj == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (message.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond messageObj.errors, view: 'edit'
            return
        }

        messageObj.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'message.label', default: 'Message'), messageObj.id])
                redirect messageObj
            }
            '*' { respond messageObj, [status: OK] }
        }
    }

    @Transactional
    def delete(Message messageObj) {

        if (messageObj == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        messageObj.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'message.label', default: 'Message'), messageObj.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'message.label', default: 'Message'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
