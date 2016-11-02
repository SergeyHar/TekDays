package tekdays

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.apache.commons.lang.builder.HashCodeBuilder

@ToString(cache=true, includeNames=true, includePackage=false)
class TekUserTekUserRole implements Serializable {

    private static final long serialVersionUID = 1

    TekUser tekUser
    TekUserRole tekUserRole

    @Override
    boolean equals(other) {
        if (other instanceof TekUserTekUserRole) {
            other.tekUserId == tekUser?.id && other.tekUserRoleId == tekUserRole?.id
        }
    }

    @Override
    int hashCode() {
        def builder = new HashCodeBuilder()
        if (tekUser) builder.append(tekUser.id)
        if (tekUserRole) builder.append(tekUserRole.id)
        builder.toHashCode()
    }

    static TekUserTekUserRole get(long tekUserId, long tekUserRoleId) {
        criteriaFor(tekUserId, tekUserRoleId).get()
    }

    static boolean exists(long tekUserId, long tekUserRoleId) {
        criteriaFor(tekUserId, tekUserRoleId).count()
    }

    private static DetachedCriteria criteriaFor(long tekUserId, long tekUserRoleId) {
        TekUserTekUserRole.where {
            tekUser == TekUser.load(tekUserId) &&
                    tekUserRole == TekUserRole.load(tekUserRoleId)
        }
    }

    static TekUserTekUserRole create(TekUser tekUser, TekUserRole tekUserRole) {
        def instance = new TekUserTekUserRole(tekUser: tekUser, tekUserRole: tekUserRole)
        instance.save()
        instance
    }

    static boolean remove(TekUser u, TekUserRole r) {
        if (u != null && r != null) {
            TekUserTekUserRole.where { tekUser == u && tekUserRole == r }.deleteAll()
        }
    }

    static int removeAll(TekUser u) {
        u == null ? 0 : TekUserTekUserRole.where { tekUser == u }.deleteAll()
    }

    static int removeAll(TekUserRole r) {
        r == null ? 0 : TekUserTekUserRole.where { tekUserRole == r }.deleteAll()
    }

    static constraints = {
        tekUserRole validator: { TekUserRole r, TekUserTekUserRole ur ->
            if (ur.tekUser?.id) {
                TekUserTekUserRole.withNewSession {
                    if (TekUserTekUserRole.exists(ur.tekUser.id, r.id)) {
                        return ['userRole.exists']
                    }
                }
            }
        }
    }

    static mapping = {
        id composite: ['tekUser', 'tekUserRole']
        version false
    }
}
