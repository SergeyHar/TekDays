package tekdays

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Secured('ROLE_ADMIN')
@Transactional(readOnly = true)
class SponsorController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Sponsor.list(params), model:[sponsorCount: Sponsor.count()]
    }

    def show(Sponsor sponsor) {
        respond sponsor
    }

    def create() {
        respond new Sponsor(params)
    }

    @Transactional
    def save(Sponsor sponsor) {
        if (sponsor == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (sponsor.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond sponsor.errors, view:'create'
            return
        }

        sponsor.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sponsor.label', default: 'Sponsor'), sponsor.id])
                redirect sponsor
            }
            '*' { respond sponsor, [status: CREATED] }
        }
    }

    def edit(Sponsor sponsor) {
        respond sponsor
    }

    @Transactional
    def update(Sponsor sponsor) {
        if (sponsor == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (sponsor.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond sponsor.errors, view:'edit'
            return
        }

        sponsor.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sponsor.label', default: 'Sponsor'), sponsor.id])
                redirect sponsor
            }
            '*'{ respond sponsor, [status: OK] }
        }
    }

    @Transactional
    def delete(Sponsor sponsor) {

        if (sponsor == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        sponsor.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sponsor.label', default: 'Sponsor'), sponsor.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sponsor.label', default: 'Sponsor'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
