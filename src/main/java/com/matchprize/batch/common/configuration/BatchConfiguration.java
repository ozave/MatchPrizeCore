package com.matchprize.batch.common.configuration;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.matchprize.batch.common.model.MatchDay;
import com.matchprize.batch.common.model.Player;
import com.matchprize.batch.jobs.matchday.CustomMatchDayItemWriter;
import com.matchprize.batch.jobs.matchday.MatchDayItemProcessor;
import com.matchprize.batch.jobs.player.CustomPlayerItemWriter;
import com.matchprize.batch.jobs.player.PlayerItemProcessor;
import com.mongodb.DB;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	
	private RedisTemplate template;
	private MongoTemplate mongoTemplate;
	
    @Autowired
    private JobBuilderFactory jobs;
 
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    
    @Autowired
    private MongoDbFactory mongo;

    // tag::readerwriterprocessor[]
//    @Bean
//    public ItemReader<Person> reader() {
//        FlatFileItemReader<Person> reader = new FlatFileItemReader<Person>();
//        reader.setResource(new ClassPathResource("sample-data.csv"));
//        reader.setLineMapper(new DefaultLineMapper<Person>() {{
//            setLineTokenizer(new DelimitedLineTokenizer() {{
//                setNames(new String[] { "firstName", "lastName" });
//            }});
//            setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
//                setTargetType(Person.class);
//            }});
//        }});
//        return reader;
//    }
	
//  @Bean
//  public ItemWriter<Person> writer(DataSource dataSource) {
//      JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<Person>();
//      writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
//      writer.setSql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)");
//      writer.setDataSource(dataSource);
//      return writer;
//  }
	
//  @Bean
//  public ItemWriter<Person> writer(MongoDbFactory mongo) { 
//  	this.mongoTemplate = new MongoTemplate(mongo); 
//  	MongoItemWriter<Person> writer = new MongoItemWriter<Person>();
//  	writer.setCollection("test");
//  	writer.setTemplate(mongoTemplate);
//  	return writer;
//  }
//////////////////////Player Total ////////////////////////////////////////
	
    @Bean
    public ItemReader<Player> playerTotalReader() {
    	this.mongoTemplate = new MongoTemplate(mongo); 
    	MongoItemReader<Player> reader = new MongoItemReader<Player>();
    	reader.setCollection("players");
    	reader.setTemplate(mongoTemplate);
    	reader.setTargetType(Player.class);
    	HashMap<String, Sort.Direction> sort = new HashMap<>();
        sort.put("_id", Sort.Direction.ASC);
        reader.setSort(sort);
        System.out.println("PlayerTotal reader");
    	reader.setQuery("{}");       
        return reader;
    }
    
    @Bean
    public ItemProcessor<Player, Player> playerTotalProcessor() {
        return new PlayerItemProcessor();
    }
    
    @Bean
    public ItemWriter<Player> playerTotalWriter() { 
    	this.mongoTemplate = new MongoTemplate(mongo); 
    	CustomPlayerItemWriter writer = new CustomPlayerItemWriter(mongoTemplate);
    	return writer;
    }
  @Bean
  public Job playerTotalJob() {
      return jobs.get("playerTotalJob")
              .incrementer(new RunIdIncrementer())
              .flow(playerTotalStep())
              .end()
              .build();
  }

  @Bean
  public Step playerTotalStep() {
      return stepBuilderFactory.get("playerTotalStep")
              .<Player, Player> chunk(1000)
              .reader(playerTotalReader())
              .processor(playerTotalProcessor())
              .writer(playerTotalWriter())
              .build();
  }
	
  /////////////////////MatchDay fixtures///////////////////////////////////
   
   @Bean
   public ItemReader<MatchDay> matchDayReader() { 
   	this.mongoTemplate = new MongoTemplate(mongo); 
	MongoItemReader<MatchDay> reader = new MongoItemReader<MatchDay>();
	reader.setCollection("matchDays");
	reader.setTemplate(mongoTemplate);
	reader.setTargetType(MatchDay.class);
	HashMap<String, Sort.Direction> sort = new HashMap<>();
    sort.put("_id", Sort.Direction.ASC);
    reader.setSort(sort);
	reader.setQuery("{}");
   	System.out.println("Matchday reader");
   	return reader;
   }
   
   @Bean
   public ItemProcessor<MatchDay, MatchDay> matchDayProcessor() {
       return new MatchDayItemProcessor();
   }
   
 @Bean
   public ItemWriter<MatchDay> matchDayWriter() { 
 	 this.mongoTemplate = new MongoTemplate(mongo); 
 	 CustomMatchDayItemWriter writer = new CustomMatchDayItemWriter(mongoTemplate);
 	 return writer;
 }
 
 @Bean
 public Job matchDayJob() {
     return jobs.get("matchDayJob")
             .incrementer(new RunIdIncrementer())
             .flow(matchDayStep1())
             .end()
             .build();
 }
 
 @Bean
 public Step matchDayStep1() {
     return stepBuilderFactory.get("matchDayStep1")
             .<MatchDay, MatchDay> chunk(200)
             .reader(matchDayReader())
             .processor(matchDayProcessor())
             .writer(matchDayWriter())
             .build();
 }
 
 
    
//  ///////////////////////////////////////////////////////////////////////////////////////////League total
//    
//     public ItemReader<League> reader2(MongoDbFactory mongo) {
//    	this.mongoTemplate = new MongoTemplate(mongo); 
//    	MongoItemReader<League> reader = new MongoItemReader<League>();
//    	reader.setCollection("leagues");
//    	reader.setTemplate(mongoTemplate);
//    	reader.setTargetType(League.class);
//    	HashMap<String, Sort.Direction> sort = new HashMap<>();
//        sort.put("_id", Sort.Direction.ASC);
//        reader.setSort(sort);
//    	reader.setQuery("{}");       
//        return reader;
//    }
//    
//    @Bean
//    public ItemReader<League> reader(MongoDbFactory mongo) { 
//    	ItemReader<League> reader2 = reader2(mongo);
//    	ItemReader<League> reader = new CompositeLeagueItemReader(reader2, mongoTemplate);
//    	System.out.println("league-composite-call");
//    	return reader;
//    }
//    
//    @Bean
//    public ItemProcessor<League, League> processor() {
//        return new LeagueItemProcessor();
//    }
//    
//  @Bean
//    public ItemWriter<League> writer(MongoDbFactory mongo) { 
//  	 this.mongoTemplate = new MongoTemplate(mongo); 
//  	 CustomLeagueMongoItemWriter writer = new CustomLeagueMongoItemWriter(mongoTemplate);
//  	 return writer;
//  }
//  
//  @Bean
//  public Job importUserJob(JobBuilderFactory jobs, Step s1, Step s2) {
//      return jobs.get("importUserJob")
//              .incrementer(new RunIdIncrementer())
//              .flow(s1)// create simplejobbuilder
//              .end()
//              .build();//create job
//  }
//  
//  @Bean
//  public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<League> reader,
//          ItemWriter<League> writer, ItemProcessor<League, League> processor) {
//      return stepBuilderFactory.get("step1")
//              .<League, League> chunk(1)
//              .reader(reader)
//              .processor(processor)
//              .writer(writer)
//              .build();
//  }
	
	  ///////////////////////////////////////////////////////////////////////////////////////////Team total
    
//    public ItemReader<FFTeam> reader2(MongoDbFactory mongo) {
//   	this.mongoTemplate = new MongoTemplate(mongo); 
//   	MongoItemReader<FFTeam> reader = new MongoItemReader<FFTeam>();
//   	reader.setCollection("teams");
//   	reader.setTemplate(mongoTemplate);
//   	reader.setTargetType(FFTeam.class);
//   	HashMap<String, Sort.Direction> sort = new HashMap<>();
//       sort.put("_id", Sort.Direction.ASC);
//       reader.setSort(sort);
//   	reader.setQuery("{}");       
//       return reader;
//   }
//   
//   @Bean
//   public ItemReader<FFTeam> reader(MongoDbFactory mongo) { 
//   	ItemReader<FFTeam> reader2 = reader2(mongo);
//   	ItemReader<FFTeam> reader = new CompositeTeamItemReader(reader2, mongoTemplate);
//   	System.out.println("composite-call");
//   	return reader;
//   }
//   
//   @Bean
//   public ItemProcessor<FFTeam, FFTeam> processor() {
//       return new TeamItemProcessor();
//   }
//   
// @Bean
//   public ItemWriter<FFTeam> writer(MongoDbFactory mongo) { 
// 	 this.mongoTemplate = new MongoTemplate(mongo); 
// 	 CustomTeamMongoItemWriter writer = new CustomTeamMongoItemWriter(mongoTemplate);
// 	 return writer;
// }
// 
// @Bean
// public Job importUserJob(JobBuilderFactory jobs, Step s1, Step s2) {
//     return jobs.get("importUserJob")
//             .incrementer(new RunIdIncrementer())
//             .flow(s1)// create simplejobbuilder
//             .end()
//             .build();//create job
// }
// 
// @Bean
// public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<FFTeam> reader,
//         ItemWriter<FFTeam> writer, ItemProcessor<FFTeam, FFTeam> processor) {
//     return stepBuilderFactory.get("step1")
//             .<FFTeam, FFTeam> chunk(1)
//             .reader(reader)
//             .processor(processor)
//             .writer(writer)
//             .build();
// }
  
  
    
////////////////////////////////////////////////////////////////////////////////////////////////
    // end::readerwriterprocessor[]

    // tag::jobstep[]


    // end::jobstep[]
    
//	 @Bean
//	 public MongoTemplate mongoTemplate(MongoDbFactory mongo) throws Exception { 
//		this.mongoTemplate = new MongoTemplate(mongo); 
//		return mongoTemplate; 
//	}

//    @Bean
//    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
    

}