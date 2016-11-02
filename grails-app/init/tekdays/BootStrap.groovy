package tekdays

class BootStrap {

    def init = { servletContext ->
//        if (GrailsUtil.environment == 'development') {

        if (!TekEvent.get(1)) {
            def u1 = new TekUser(fullName: 'John Doe',
                    username: 'jdoe',
                    password: 't0ps3cr3t',
                    email: 'jdoe@johnsgroovyshop.com',
                    website: 'blog.johnsgroovyshop.com',
                    bio: 'John has been programming for over 40 years. ...').save()
            new TekUser(fullName: 'John Deere',
                    username: 'tractorman',
                    password: 't0ps3cr3t',
                    email: 'john.deere@porkproducers.org',
                    website: 'www.perl.porkproducers.org',
                    bio: 'John is a top notch Perl programmer and a ...').save()
            new TekEvent(name: 'Gateway Code Camp',
                    city: 'Saint Louis, MO',
                    organizer: TekUser.findByFullName('John Doe').toString(),
                    venue: 'TBD',
                    startDate: new Date('9/19/2009'),
                    endDate: new Date('9/19/2009'),
                    description: 'This conference will bring coders ...').save()
            new TekEvent(name: 'Perl Before Swine',
                    city: 'Austin, MN',
                    organizer: TekUser.findByFullName('John Deere'),
                    venue: 'SPAM Museum',
                    startDate: new Date('9/1/2009'),
                    endDate: new Date('9/1/2009'),
                    description: 'Join the Perl programmers of the ...').save()

            def u2 = new TekUser(
                    fullName: 'aram',
                    username: 'aa',
                    password: '123',
                    email: 'jdoe@johnsgroovyshop.com',
                    website: 'blog.johnsgroovyshop.com',
                    bio: 'John has been programming for over 40 years. ...').save()
            def admiRole = new TekUserRole(authority: 'ROLE_ADMIN').save()
            def userRole = new TekUserRole(authority: 'ROLE_USER').save()


            TekUserTekUserRole.create u1, userRole
            TekUserTekUserRole.create u2, admiRole

            def g1 = TekEvent.findByName('Gateway Code Camp')
            g1.addToVolunteers(new TekUser(fullName: 'Sarah Martin',
                    username: 'sarah',
                    password: '54321',
                    email: 'sarah@martinworld.com',
                    website: 'www.martinworld.com',
                    bio: 'Web designer and Grails afficianado.'))
            g1.addToVolunteers(new TekUser(fullName: 'Bill Smith',
                    username: 'Mr_Bill',
                    password: '12345',
                    email: 'mrbill@email.com',
                    website: 'www.mrbillswebsite.com',
                    bio: 'Software developer and claymation artist.'))
            g1.addToRespondents('ben@grailsmail.com')
            g1.addToRespondents('zachary@linuxgurus.org')
            g1.addToRespondents('solomon@bootstrapwelding.com')
            g1.save()

            def s1 = new Sponsor(name: 'Contegix',
                    website: 'http://contegix.com',
                    description: 'Beyond Managed Hosting for your Enterprise'
            ).save()
            def s2 = new Sponsor(name: 'Object Computing Incorporated',
                    website: 'http://ociweb.com',
                    description: 'An OO Software Engineering Company'
            ).save()
            def sp1 = new Sponsorship(event: g1,
                    sponsor: s1,
                    contributionType: 'Other',
                    description: 'Cool T-Shirts',
                    nullable: 'htd')
            def sp2 = new Sponsorship(event: g1,
                    sponsor: s2,
                    contributionType: 'Venue',
                    description: 'Will be paying for the Moscone')
            s1.addToSponsorships(sp1)
            s1.save()
            s2.addToSponsorships(sp2)
            s2.save()
            g1.addToSponsorships(sp1)
            g1.addToSponsorships(sp2)
            g1.save()

            def t1 = new Task(
                    title: 'test',
                    notes: 'not',
                    assignedTo: TekUser.findByFullName('John Deere'),
                    dueDate: new Date('9/5/2009'),
                    event: TekEvent.findByName('Gateway Code Camp')
            ).save()

            def m1 = new Message(
                    subject: 'test message',
                    content: 'nullcx',
                    event: TekEvent.findByName('Gateway Code Camp'),
                    author: TekUser.findByFullName('John Doe')
            )

            def m2 = new Message(
                    subject: 'test message 2',
                    content: 'nullxcv',
                    parent: Message.findBySubject('test message'),
                    event: TekEvent.findByName('Gateway Code Camp'),
                    author: TekUser.findByFullName('John Deere')
            )

            m1.save()
            m2.save()

        }
//        }
    }
    def destroy = {
    }
}
