// Place your Spring DSL code here



beans = {

//    def conf = SpringSecurityUtils.securityConfig
//
//// custom authentication
//    authenticationProcessingFilter(GvFilter) {
//        authenticationManager = ref('authenticationManager')
//        sessionAuthenticationStrategy = ref('sessionAuthenticationStrategy')
//        authenticationSuccessHandler = ref('authenticationSuccessHandler')
//        authenticationFailureHandler = ref('authenticationFailureHandler')
//        rememberMeServices = ref('rememberMeServices')
//        authenticationDetailsSource = ref('authenticationDetailsSource')
//        filterProcessesUrl = conf.apf.filterProcessesUrl
//        usernameParameter = conf.apf.usernameParameter
//        passwordParameter = conf.apf.passwordParameter
//        continueChainBeforeSuccessfulAuthentication = conf.apf.continueChainBeforeSuccessfulAuthentication
//        allowSessionCreation = conf.apf.allowSessionCreation
//        postOnly = conf.apf.postOnly
//
//    }
//    // custom authentication
//    daoAuthenticationProvider(GvAuthenticationProvider) {
//        passwordEncoder = ref('passwordEncoder')
//        saltSource = ref('saltSource')
//        preAuthenticationChecks = ref('preAuthenticationChecks')
//        postAuthenticationChecks = ref('postAuthenticationChecks')
//        dataSource = ref('dataSource')
//    }
}