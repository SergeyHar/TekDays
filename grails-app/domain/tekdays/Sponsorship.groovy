package tekdays

class Sponsorship {

    TekEvent event
    Sponsor sponsor
    String contributionType
    String description
    String notes


    static constraints = {
        event(nullable: false)
        sponsor(nullable: false)
        contributionType(inList: ["Other", "Venue", "A/V", "Promotion", "Cash"])
        description(nullable: true, blank: true)
        notes(nullable: true, blank: true, maxSize: 5000)
    }

    String toString() {
        return event
    }
    String forTest(){
        return "$event, $sponsor, $contributionType, $description, $notes"
    }
}
