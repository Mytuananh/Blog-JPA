package anhtuan.controller;

import anhtuan.model.Blog;
import anhtuan.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private IBlogService blogService;

    @GetMapping("")
    public ModelAndView showAll(){
        List<Blog> blogList = blogService.findAll();
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("blogs", blogList);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("blog", new Blog());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView save(Blog blog) {
        ModelAndView modelAndView = new ModelAndView("redirect:/blog");
        blogService.save(blog);
        modelAndView.addObject("blog", new Blog());
        modelAndView.addObject("message", "Create New Blog Successfully!");
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editBlog(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Blog blog = blogService.findById(id);
        modelAndView.addObject("blog",blog);
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("blog") Blog blog) {
        ModelAndView modelAndView = new ModelAndView("redirect:/blog");
        blogService.save(blog);
        modelAndView.addObject("blog", blog);
        modelAndView.addObject("message", "Update blog successfully!");
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/blog");
        blogService.remove(id);
        modelAndView.addObject("message", "Delete blog successfully!");
        return modelAndView;
    }


}
