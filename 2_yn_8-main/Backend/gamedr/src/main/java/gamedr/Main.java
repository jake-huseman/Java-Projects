package gamedr;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//import gamedr.Category.CategoryForm;
//import gamedr.Category.CategoryRepository;
//import gamedr.Post.PostForm;
//import gamedr.Post.PostsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//import gamedr.Chronicle.Chronicle;
//import gamedr.Chronicle.ChronicleRepository;
import gamedr.MatchPair.MatchPair;
import gamedr.MatchPair.MatchPairRepository;
import gamedr.Matchmaker.MatchmakerRepository;
import gamedr.Mchron.Mchron;
import gamedr.Mchron.MchronRepository;
import gamedr.Moderator.Moderator;
import gamedr.Moderator.ModeratorRepository;
import gamedr.Rejection.Rejection;
import gamedr.Rejection.RejectionRepository;
import gamedr.Report.Report;
import gamedr.Report.ReportRepository;
import gamedr.Users.Actor;
import gamedr.Users.Game;
import gamedr.Users.User;
import gamedr.Users.UserRepository;
import gamedr.Report.Severity;

/**
 * 
 * @author David Dong
 * 
 */ 

@SpringBootApplication
@EnableJpaRepositories
class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    // Create 3 users with their machines
    /**
     * 
     * @param userRepository repository for the User entity
     * @param matchPairRepository
     * Creates a commandLine runner to enter dummy data into the database
     * As mentioned in User.java just associating the Laptop object with the User will save it into the database because of the CascadeType
     */
    @Bean
    CommandLineRunner initUser(
    		UserRepository userRepository, 
    		MatchPairRepository matchPairRepository,
    		ReportRepository reportRepository,
    		RejectionRepository rejectionRepository,
    		MchronRepository mchronRepository,
    		MatchmakerRepository matchmakerRepository,
    		ModeratorRepository moderatorRepository//,
//            PostsRepository postsRepository,
//            CategoryRepository categoryRepository
    	){
        return args -> {
//        	  User user1 = new User("Brad", "bradUser", "brad@somemail.com", "sample description");
//              User user2 = new User("Cale", "caleUser", "cale@somemail.com", "sample description");
//              User user3 = new User("Abhi", "abhiUser", "abhi@somemail.com", "sample description");
//              User user4 = new User("Dereck", "dereckUser", "dereck@somemail.com", "sample description");
//              User user5 = new User("Evan", "evanUser", "evan@somemail.com", "sample description");
//              user1.setActor(Actor.SKR);
//              user2.setActor(Actor.MTR);
//              user3.setActor(Actor.MDR);
//              user4.setActor(Actor.SKR);
//              user5.setActor(Actor.MTR);
//              user1.setGame(Game.FPS);
//              user2.setGame(Game.FPS);
//              user3.setGame(Game.SPORTS);
//              user4.setGame(Game.MMO);
//              user5.setGame(Game.MMO);
//              List<User> users = Arrays.asList(user1, user2, user3, user4, user5);
//              
//              MatchPair match1 = new MatchPair(user1, user2);
//              MatchPair match2 = new MatchPair(user1, user3);
//              List<MatchPair> matches = Arrays.asList(match1, match2);
//              matchPairRepository.saveAll(matches);
//              
//              user1.setInclusion(match1);
//              user1.setInclusion(match2);
//              user3.setInclusion(match2);
//              user2.setInclusion(match1);
//              userRepository.saveAll(users);
//              
////              Mchron m1 = new Mchron(user1, match1, 10);
////              Mchron m2 = new Mchron(user1, match2, 10);
////              mchronRepository.saveAll(Arrays.asList(m1, m2));
//              
//          //    user1.addMchron(m1);
//              userRepository.save(user1);
//              
////              User reportedUser = new User("Repor Ted", "reportedUser", "reported@gmail.com", "Racing");
////              Report report1 = new Report(reportedUser, Severity.MEDIAN, "desc");
////              reportedUser.setReport(report1);
////              userRepository.save(reportedUser);
////              reportRepository.save(report1);
//              
//              User u1 = new User("R1", "r1User", "r1@gmail.com", "Racing");
//              User u2 = new User("R2", "r2User", "r2@gmail.com", "Racing");
//              userRepository.save(u1);
//              userRepository.save(u2);
//              Set<User> testSet = new HashSet<User>();
//              testSet.add(u1);
//              testSet.add(u2);
//              Rejection r1 = new Rejection(true, testSet);
//              rejectionRepository.save(r1);
//              u1.addRejection(r1);
//              u2.addRejection(r1);
//              userRepository.save(u1);
//              userRepository.save(u2);
//              
//              User u3 = new User("R3", "r3User", "r3@gmail.com", "Cycling");
//              User u4 = new User("R4", "r4User", "r4@gmail.com", "Cycling");
//              userRepository.save(u3);
//              userRepository.save(u4);
//              Set<User> testSet2 = new HashSet<User>();
//              testSet2.add(u3);
//              testSet2.add(u4);
//              Rejection r2 = new Rejection(true, testSet2);
//              rejectionRepository.save(r2);
//              u3.addRejection(r2);
//              u4.addRejection(r2);
//              userRepository.save(u3);
//              userRepository.save(u4);
//
//            
            // test change
        };
    }

}
