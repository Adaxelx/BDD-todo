package stories;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.LoadFromURL;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import stories.AddTaskSteps;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;
import static org.jbehave.core.reporters.Format.TXT;
import static org.jbehave.core.reporters.Format.XML;
import static org.jbehave.core.reporters.StoryReporterBuilder.Format.*;



//public class StoriesConfig extends JUnitStories {
//    public StoriesConfig() {
//        // configure as TraderStory except for
//        Configuration configuration = new MostUsefulConfiguration()
//                .useStoryLoader(new LoadFromURL());
//    }
//
//    @Override
//    protected List<String> storyPaths() {
//        String codeLocation = codeLocationFromClass(this.getClass()).getFile();
//        System.out.println(codeLocation);
//        return new StoryFinder().findPaths(codeLocation, asList("**/*.story"),
//                asList(""), "file:"+codeLocation);
//    }
//
//
//}

public class StoriesConfig extends JUnitStories {

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryReporterBuilder(new StoryReporterBuilder().withDefaultFormats().withFormats(CONSOLE, TXT, HTML));
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new AddTaskSteps(), new UpdateTaskSteps(), new SaveTasksToFileSteps());
    }

    protected List<String> storyPaths() {
        StoryFinder finder = new StoryFinder();
        return finder.findPaths(codeLocationFromClass(this.getClass()).getFile(), Arrays.asList("**/*.story"), Arrays.asList(""));
    }

//    @Override
//    protected List<String> storyPaths() {
//        String codeLocation = codeLocationFromClass(this.getClass()).getFile();
//        System.out.println(codeLocation);
//        return new StoryFinder().findPaths(codeLocation, asList("**.story"),
//                asList(""), "file:"+codeLocation);
//    }

}

//public class StoriesConfig extends JUnitStory {
//
//    // Here we specify the configuration, starting from default MostUsefulConfiguration, and changing only what is needed
//    @Override
//    public Configuration configuration() {
//        return new MostUsefulConfiguration()
//                // where to find the stories
//                .useStoryLoader(new LoadFromClasspath(this.getClass()))
//                // CONSOLE and TXT reporting
//                .useStoryReporterBuilder(new StoryReporterBuilder().withDefaultFormats().withFormats(Format.CONSOLE, Format.TXT));
//    }
//
//    // Here we specify the steps classes
//    @Override
//    public InjectableStepsFactory stepsFactory() {
//        // varargs, can have more that one steps classes
//        return new InstanceStepsFactory(configuration(), new AddTaskSteps());
//    }
//
//}