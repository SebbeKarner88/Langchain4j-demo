package org.vaadin.marcus.langchain4j;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;
import org.vaadin.marcus.service.ToolService;

@Component
public class  LangChain4jTools {

    @Tool("""
            Retrieves a good advice
            """)
    public String getAdvice() {
        return ToolService.getAdvice();
    }

    @Tool("""
            Retrieves a random joke. The jokes are family-friendly and avoid sensitive topics.
            """)
    public String getJoke() {
        return ToolService.getJoke();
    }

    @Tool("""
            Retrieves a random fact about cats
            """)
    public String getCatFact() {
        return ToolService.getCatFact();
    }

    @Tool("""
            Retrieves an inspirational quote with its author
            """)
    public String getQuote() {
        return ToolService.getQuote();
    }

    @Tool("""
            Retrieves an interesting fact about a specific number

            Parameters:
            number - The number to get a fact about
            """)
    public String getNumberFact(int number) {
        return ToolService.getNumberFact(number);
    }

    @Tool("""
            Retrieves current weather information for a specified city

            Parameters:
            city - The name of the city to get weather for
            """)
    public String getWeather(String city) {
        return ToolService.getWeather(city);
    }
}
