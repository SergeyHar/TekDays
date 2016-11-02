package tekdays

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes = 'username')
@ToString(includes = 'username', includeNames = true, includePackage = false)
class TekUser implements Serializable {

    private static final long serialVersionUID = 1

    transient springSecurityService

    String fullName
    String username
    String password
    String email
    String website
    String bio

    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired


    Set<TekUserRole> getAuthorities() {
        TekUserTekUserRole.findAllByTekUser(this)*.tekUserRole
    }

    String toString() { fullName }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
    }

    static transients = ['springSecurityService']

//    static hasMany = [role: TekUserRole]
    static constraints = {
        fullName()
        username blank: false, unique: true
        password blank: false, password: true
        email(email: true)
        website()
        bio(maxSize: 5000)
//        role nullable: true
    }

    static mapping = {
        password column: '`password`'
    }

}
