package tekdays

class TekEvent {


    String city
    String name
    TekUser organizer
    String venue
    Date startDate
    Date endDate
    String description

    String toString() {
        "$name, $city"
    }

    static hasMany = [volunteers  : TekUser,
                      respondents : String,
                      sponsorships: Sponsorship,
                      tasks       : Task,
                      messages    : Message]

    static constraints = {
        name lank: false
        city blank: false
        description nullable: true, maxSize: 5000
        organizer()
        venue()
        startDate()
        endDate()
        volunteers nullable: true
        sponsorships nullable: true
        tasks nullable: true
        messages nullable: true

    }

}
