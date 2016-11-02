package tekdays

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Secured('ROLE_ADMIN')
@Transactional(readOnly = true)
class SponsorshipController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Sponsorship.list(params), model:[sponsorshipCount: Sponsorship.count()]
    }

    def show(Sponsorship sponsorship) {
        respond sponsorship
    }

    def create() {
        respond new Sponsorship(params)
    }

    @Transactional
    def save(Sponsorship sponsorship) {
        if (sponsorship == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (sponsorship.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond sponsorship.errors, view:'create'
            return
        }

        sponsorship.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sponsorship.label', default: 'Sponsorship'), sponsorship.id])
                redirect sponsorship
            }
            '*' { respond sponsorship, [status: CREATED] }
        }
    }

    def edit(Sponsorship sponsorship) {
        respond sponsorship
    }

    @Transactional
    def update(Sponsorship sponsorship) {
        if (sponsorship == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (sponsorship.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond sponsorship.errors, view:'edit'
            return
        }

        sponsorship.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sponsorship.label', default: 'Sponsorship'), sponsorship.id])
                redirect sponsorship
            }
            '*'{ respond sponsorship, [status: OK] }
        }
    }

    @Transactional
    def delete(Sponsorship sponsorship) {

        if (sponsorship == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        sponsorship.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sponsorship.label', default: 'Sponsorship'), sponsorship.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sponsorship.label', default: 'Sponsorship'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
