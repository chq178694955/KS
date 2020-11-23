package com.king.framework.excel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @创建人 chq
 * @创建时间 2020/9/4
 * @描述
 */
@Component
public class ExcelParserFactory implements ApplicationListener<ContextRefreshedEvent> {

    private static Map<Integer,IExcelParserMgrService<ExcelParser>> parserMap = new ConcurrentHashMap<>();

    private ExcelParserFactory instance;

    public ExcelParserFactory getInstance(){
        if(null == instance){
            synchronized (ExcelParserFactory.class){
                if(null == instance){
                    instance = new ExcelParserFactory();
                }
            }
        }
        return instance;
    }

    @Autowired
    private List<IExcelParserMgrService<ExcelParser>> parsers;

    public IExcelParserMgrService<ExcelParser> getParser(int parserType){
        return parserMap.get(parserType);
    }

    public IExcelParserMgrService<ExcelParser> getParser(ExcelParserType excelParserType){
        return excelParserType == null ? null : getParser(excelParserType.getValue());
    }

    public List<IExcelParserMgrService<ExcelParser>> getParsers(){
        List<IExcelParserMgrService<ExcelParser>> parsers = new ArrayList<>();
        for(Map.Entry<Integer,IExcelParserMgrService<ExcelParser>> entry : parserMap.entrySet()){
            parsers.add(entry.getValue());
        }
        return parsers;
    }

    public void setParsers(List<IExcelParserMgrService<ExcelParser>> parsers){
        this.parsers = parsers;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(contextRefreshedEvent.getApplicationContext().getParent() == null){
            if(parsers != null){
                for(IExcelParserMgrService<ExcelParser> parser : parsers){
                    if(parser.getParserType() != null){
                        if(parser.getParserType().getValue() != ExcelParserType.UNKNOW.getValue()){
                            parserMap.put(parser.getParserType().getValue(),parser);
                        }
                    }
                }
            }
        }
    }

}
