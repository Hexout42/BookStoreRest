package ru.lernup.bookstore.security;

//@Slf4j
//@Component
//
//public class AuthProvider implements AuthenticationProvider {
//    private final UserService userService;
//
//    public AuthProvider(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String name = authentication.getName();
//        String password = String.valueOf(authentication.getCredentials());
//        log.info("log : {},password {} ", name,password);
//        UserDto user = userService.getUserByLogin(name);
//
//        if (user == null){
//            throw new UsernameNotFoundException("NOT FOUND USERNAME");
//        }
//        if(!user.getPassword().equals(password)){
//            throw  new BadCredentialsException("Неверный пароль");
//        }
//        user.getRoles().forEach(userRole -> {
//            log.info(userRole.getAuthority());
//        });
//        return new UsernamePasswordAuthenticationToken(user,password,user.getRoles());
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//
//        return true;
//    }
//}
