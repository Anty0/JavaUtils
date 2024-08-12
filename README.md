# Java Utils

> [!IMPORTANT]
> Code in this repository is not actively maintained, but feel free to create issues or pull requests if you find something that needs fixing.
> The repository was created during times of Java 7 and 8, so ... you know what that means.

This repository contains a collection of Java utilities that I have written during my high school years when I was mostly programming in Java and Kotlin for Android.

Repository is organized as a single library you can include in a project. It uses Gradle as a build system. Each namespace contains all the utilities that are related to a specific topic.

## Usage

> [!NOTE]
> Project is currently not published to any Maven repository. Tell me if you want me to publish it (or maybe split part of it into a separate library and publish that).

> [!NOTE]
> If you plan to also use the AndroidUtils library, follow the instructions in that repository as that will include both libraries in your project.

To include this library in your project, add the following to your `build.gradle`:

```gradle
dependencies {
    implementation project(path: ':javautils')
}
```

Then add the repository as a submodule:

```bash
git submodule add javautils git@github.com:Anty0/JavaUtils.git
```

## Features

- `eu.codetopic.java.utils.access` - At some point I started to play with weak references as a solution to avoid holding UI data longer than needed when doing some background work. Idea was that I can start a background task and if the UI is destroyed before the task finishes, the task won't hold the UI data in memory. When the task reaches the point where it needs to apply the result to the UI, it will get a null reference to the UI and will not do anything. Combine this with interrupting running tasks (no need to wait for them to actually finish) and the garbage collector will be happy since most of the references will have only a single reference to them outside of stack.
- `eu.codetpic.java.utils.collections.queue` - Custom queue implementations, because why not
- `eu.codetopic.java.utils.debug` - Global `DebugMode` switch which I used in my projects ; there is also a collection of assert functions that only work when `DebugMode` is enabled (I used them to detect bugs which when ignored would not cause big problems - without these asserts I would probably never find such bugs)
- `eu.codetopic.java.utils.exception` - Exception classes that are used in other utilities and projects
- `eu.codetopic.java.utils.log` - Because for some reason I had to write my own logger ; Main "feature" was that you can listen for log messages and react to them ; There is only one global logger which sends log messages to all listeners
- `eu.codetopic.java.utils.reflect` - Place for playing with Java internals ; Contains utilities for working with Java reflection
  - `annotation` - Utilities for working with annotations at runtime
  - `field` - Recursive search for fields matching certain criteria great for writing debugging tools
- `eu.codetopic.java.utils.simple` - One-off utilities that don't fit anywhere else
  - `IteratorWrapper` - There is no OOP without some unnecessary wrapping :) ; on a serious note, used to use this to modify behavior of existing instance of iterator, this is no longer needed with Kotlins delegation feature which can be used to achieve the same thing
  - `ListIteratorWrapper` - Same as above but for list iterators
  - `SimpleLock` - Lock implemented using Java `synchronized` feature ; Probably created because I wanted to have a lock that can be locked in one thread and unlocked in another (something that is not possible with Java's ReentrantLock), but there are better ways to do this
  - `SimpleSuspendLock` - Having learned nothing from the previous attempt, I created a lock that leverages Kotlin's coroutines ; As long as you are not using this lock without coroutines, it is actually not a bad implementation ; It queues up the coroutines that are waiting for the lock and resumes them in the order they were queued, but I would be surprised if there are no better implementations of this already available (there were none at the time when I created this)
- `eu.codetopic.java.utils.ArrayTools` - When you need to work with arrays as if they were collections (usually not advised)
- `eu.codetopic.java.utils.extensions` - Kotlin extensions for Java classes
  - Created during early days of Kotlin
  - Basically every time I was missing something in Kotlin I created an extension for it
- `eu.codetopic.java.utils.Objects` - Backport of `java.util.Objects`, no longer useful (kept as my old projects use it)
