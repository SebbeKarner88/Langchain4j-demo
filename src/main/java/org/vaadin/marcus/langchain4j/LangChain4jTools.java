package org.vaadin.marcus.langchain4j;

import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.service.MemoryId;
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

    @Tool("""
            Helps the user plan a trip in multiple steps. This tool guides the user through a step-by-step process to plan their trip.
            Use this tool when the user wants to plan a trip or vacation.

            Parameters:
            input - The user's response to the current step's question
            """)
    public String planTrip(@MemoryId String chatId, String input) {
        return ToolService.planTripMultiStep(chatId, input);
    }
}
