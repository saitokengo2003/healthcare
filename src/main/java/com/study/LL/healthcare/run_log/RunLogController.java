package com.study.LL.healthcare.run_log;


import com.study.LL.healthcare.user.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class RunLogController {

  /* ログインチェック用サービス */
  @Autowired
  private LoginService loginService;

  /* タスク管理の業務ロジッククラス */
  @Autowired
  private RunLogService runLogService;

  @GetMapping("/runLog")
  public String getrunLogList(Model model) {
    // ログインチェック
    if (!loginService.isLogin()) return "login";

    System.out.println("[getrunLogList1]:"+ loginService.getLoginUserId());
    RunLogEntity runLogEntity = runLogService.selectAll(loginService.getLoginUserId());
    System.out.println("[runLogEntity2]:" + runLogEntity);
    model.addAttribute("runLogEntity", runLogEntity);

    return "runLog/list";
  }


  @PostMapping("/runLog/insert")
  public String insertRunLog(
    @RequestParam(name = "title") String title,
    @RequestParam(name = "limit") String limit,
    @RequestParam(name = "distance") float distance,
    @RequestParam(name = "timelog") float timelog,
    Model model
  ) {
    // 入力チェック
    boolean isValid = runLogService.validate(title, limit);
    if (!isValid) {
      model.addAttribute("errorMessage", "入力項目に不備があります");
      return getrunLogList(model);
    }

    // 実行結果を取得
    boolean isSuccess = runLogService.insert(loginService.getLoginUserId(), title, limit, distance, timelog);
    if (isSuccess) {
      model.addAttribute("message", "正常に登録されました");
    } else {
      model.addAttribute("errorMessage", "登録できませんでした。再度登録し直してください");
    }

    return getrunLogList(model);
  }


  @PostMapping("/runLog/delete")
  public String deleteRunLog(@RequestParam(name = "id") String id, Model model) {
    boolean isValid = runLogService.validate(id);
    if (!isValid) {
      return "index";
    }

    boolean isSuccess = runLogService.delete(id);
    if (isSuccess) {
      model.addAttribute("message", "正常に削除されました");
    } else {
      model.addAttribute("errorMessage", "削除できませんでした。再度登録し直してください");
    }
    return getrunLogList(model);
  }


  @PostMapping("/runLog/complete")
  public String completerunLog(@RequestParam(name = "id") String id, Model model) {
    boolean isValid = runLogService.validate(id);
    if (!isValid) {
      return "index";
    }

    boolean isSuccess = runLogService.complete(id);
    if (isSuccess) {
      model.addAttribute("message", "正常に更新されました");
    } else {
      model.addAttribute("errorMessage", "更新できませんでした。再度登録し直してください");
    }
    return getrunLogList(model);
  }
}
