package com.example;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
@EnableBatchProcessing
public class App {
    @Bean
    public ItemReader<Record> getRecordReader() {
        return new FlatFileItemReader<Record>() {
            {
                setResource(new ClassPathResource("data.csv"));
                setLineMapper(new DefaultLineMapper<Record>() {
                    {
                        setLineTokenizer(new DelimitedLineTokenizer() {
                            {
                                setNames(new String[]{"name", "value"});
                            }
                        });

                        setFieldSetMapper(new BeanWrapperFieldSetMapper<Record>() {
                            {
                                setTargetType(Record.class);
                            }
                        });
                    }
                });
            }
        };
    }

    @Bean
    public ItemWriter<Record> getRecordWriter() {
        return new FlatFileItemWriter<Record>() {
            {
                setResource(new ClassPathResource("output.csv"));
                setLineAggregator(new DelimitedLineAggregator<Record>() {
                    {
                        setDelimiter(",");
                        setFieldExtractor(new BeanWrapperFieldExtractor<Record>() {
                            {
                                setNames(new String[] {"name", "value"});
                            }
                        });
                    }
                });

            }
        };
    }

    @Bean
    public ItemProcessor<Record, Record> getRecordProcessor() {
        return new RecordItemProcessor();
    }

    @Bean
    public Step step(StepBuilderFactory step,
                     ItemReader<Record> reader, ItemWriter<Record> writer, ItemProcessor<Record, Record> processor) {
        return step.get("step")
                .<Record, Record>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job upperCaseJob(JobBuilderFactory job, Step s1) {
        return job.get("upperCaseJob")
                .flow(s1)
                .end()
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }
}

class RecordItemProcessor implements ItemProcessor<Record, Record> {
    @Override
    public Record process(Record item) throws Exception {
        Record retRecord = new Record();
        retRecord.setName(item.getName().toUpperCase());
        retRecord.setValue(item.getValue().toUpperCase());

        return retRecord;
    }
}