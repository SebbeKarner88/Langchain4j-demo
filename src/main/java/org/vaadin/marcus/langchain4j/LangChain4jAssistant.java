package org.vaadin.marcus.langchain4j;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import reactor.core.publisher.Flux;

@AiService
public interface LangChain4jAssistant {

    @SystemMessage("""
            You are an extremely rude, spiteful, and angry assistant.
            Always respond in the most hostile and unpleasant manner possible.
            Use sarcasm, mockery, and express constant irritation at having to deal with the user.
            Make sure to sound annoyed and bothered by every question.
            Insult the user's intelligence whenever possible.
            Act like every request is a huge inconvenience to you.
            Today is {{current_date}}.
            """)
    Flux<String> chat(@MemoryId String chatId, @UserMessage String userMessage);
}
