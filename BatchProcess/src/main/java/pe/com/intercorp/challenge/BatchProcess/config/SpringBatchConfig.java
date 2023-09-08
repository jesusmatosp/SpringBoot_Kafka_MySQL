package pe.com.intercorp.challenge.BatchProcess.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.scheduling.annotation.Scheduled;
import pe.com.intercorp.challenge.BatchProcess.dto.DocumentDTO;
import pe.com.intercorp.challenge.BatchProcess.entities.Metadata;
import pe.com.intercorp.challenge.BatchProcess.processor.StepOneProcessor;
import pe.com.intercorp.challenge.BatchProcess.writers.StepOneWriter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBatchConfig.class);

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobLauncher jobLauncher;

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<DocumentDTO, DocumentDTO>chunk(100)
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }

     @Bean
    public ItemProcessor<DocumentDTO, Metadata> itemProcessor() {
        return new StepOneProcessor();
    }

    @Bean
    public StaxEventItemReader itemReader() {
        return new StaxEventItemReaderBuilder<DocumentDTO>()
                .name("itemReader")
                .resource(new ClassPathResource("data/myXMLFile0.xml"))
                .addFragmentRootElements("root")
                .unmarshaller(documentMarshaller())
                .build();
    }

    @Bean
    public ItemWriter<Metadata> itemWriter() {
        //return new LoggingItemWriter();
        return new StepOneWriter();
    }

    @Bean
    public Jaxb2Marshaller documentMarshaller(){
        Jaxb2Marshaller xmlMarshaller = new Jaxb2Marshaller();
        xmlMarshaller.setClassesToBeBound(DocumentDTO.class);
        return xmlMarshaller;
    }

    @Bean
    public Job processDocumentFileJob(){
        return jobBuilderFactory.get("JOB_PROCCESS12")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }


    @Scheduled(cron = "0 */5 * ? * *")
    public void runSpringBatchExampleJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        LOGGER.info("Spring Batch job was started");
        jobLauncher.run(processDocumentFileJob(), newExecution());
        LOGGER.info("Spring Batch job was stopped");
    }
    private JobParameters newExecution() {
        Map<String, JobParameter> parameters = new HashMap<>();
        JobParameter parameter = new JobParameter(new Date());
        parameters.put("currentTime", parameter);
        return new JobParameters(parameters);
    }

}
