package com.ali.user.open.history;

import android.text.TextUtils;
import com.ali.user.open.core.AliMemberSDK;
import com.ali.user.open.core.config.ConfigManager;
import com.ali.user.open.core.service.StorageService;
import com.yunos.tvtaobao.payment.utils.TvTaoSharedPerference;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AccountHistoryManager {
    private static final String HISTORY_LOGIN_ACCOUNTS = "tb_history_acounts";
    private static volatile AccountHistoryManager singleton;

    private AccountHistoryManager() {
    }

    public static AccountHistoryManager getInstance() {
        if (singleton == null) {
            synchronized (AccountHistoryManager.class) {
                if (singleton == null) {
                    singleton = new AccountHistoryManager();
                }
            }
        }
        return singleton;
    }

    public void clearHistoryAccount() {
        ((StorageService) AliMemberSDK.getService(StorageService.class)).removeDDpExValue(HISTORY_LOGIN_ACCOUNTS);
    }

    public void removeHistoryAccount(HistoryAccount historyAccount) {
        List<HistoryAccount> accountList;
        String loginHistoryJson;
        if (historyAccount != null) {
            try {
                ((StorageService) AliMemberSDK.getService(StorageService.class)).removeSafeToken(historyAccount.tokenKey);
                try {
                    loginHistoryJson = ((StorageService) AliMemberSDK.getService(StorageService.class)).getDDpExValue(HISTORY_LOGIN_ACCOUNTS);
                } catch (Exception e) {
                }
                if (TextUtils.isEmpty(loginHistoryJson)) {
                    accountList = new ArrayList<>();
                } else {
                    accountList = parseObject(loginHistoryJson);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
                accountList = new ArrayList<>();
                ((StorageService) AliMemberSDK.getService(StorageService.class)).removeDDpExValue(HISTORY_LOGIN_ACCOUNTS);
            } catch (Throwable t) {
                t.printStackTrace();
                return;
            }
            if (accountList != null) {
                List<HistoryAccount> tmp = new ArrayList<>();
                for (HistoryAccount h : accountList) {
                    if (!h.userId.equals(historyAccount.userId)) {
                        tmp.add(h);
                    }
                }
                accountList = tmp;
            }
            if (accountList == null) {
                return;
            }
            if (accountList == null || accountList.isEmpty()) {
                ((StorageService) AliMemberSDK.getService(StorageService.class)).removeDDpExValue(HISTORY_LOGIN_ACCOUNTS);
            } else {
                ((StorageService) AliMemberSDK.getService(StorageService.class)).putDDpExValue(HISTORY_LOGIN_ACCOUNTS, toJSONString(accountList));
            }
        }
    }

    public void putLoginHistory(HistoryAccount historyAccount, String salt) {
        if (!ConfigManager.getInstance().isSaveHistoryWithSalt() || ((StorageService) AliMemberSDK.getService(StorageService.class)).saveSafeToken(historyAccount.tokenKey, salt)) {
            List<HistoryAccount> historyAccountList = getHistoryAccounts();
            if (historyAccountList != null) {
                List<HistoryAccount> historyAccountList2 = new ArrayList<>();
                historyAccountList2.add(historyAccount);
                for (HistoryAccount ha : historyAccountList) {
                    if (historyAccountList2.size() >= ConfigManager.getInstance().getMaxHistoryAccount()) {
                        break;
                    } else if (TextUtils.isEmpty(ha.userId) || !ha.userId.equals(historyAccount.userId)) {
                        historyAccountList2.add(ha);
                    }
                }
                ((StorageService) AliMemberSDK.getService(StorageService.class)).putDDpExValue(HISTORY_LOGIN_ACCOUNTS, toJSONString(historyAccountList2));
                return;
            }
            List<HistoryAccount> historyAccountList3 = new ArrayList<>();
            historyAccountList3.add(historyAccount);
            ((StorageService) AliMemberSDK.getService(StorageService.class)).putDDpExValue(HISTORY_LOGIN_ACCOUNTS, toJSONString(historyAccountList3));
        }
    }

    public HistoryAccount findHistoryAccount(String userid) {
        try {
            List<HistoryAccount> accounts = getHistoryAccounts();
            if (accounts == null) {
                return null;
            }
            for (HistoryAccount ha : accounts) {
                if (ha.userId != null && ha.userId.equals(userid)) {
                    return ha;
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:5:0x0010  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.ali.user.open.history.HistoryAccount matchHistoryAccount(java.lang.String r5) {
        /*
            r4 = this;
            java.util.List r1 = r4.getHistoryAccounts()
            if (r1 == 0) goto L_0x002f
            java.util.Iterator r2 = r1.iterator()
        L_0x000a:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002f
            java.lang.Object r0 = r2.next()
            com.ali.user.open.history.HistoryAccount r0 = (com.ali.user.open.history.HistoryAccount) r0
            java.lang.String r3 = r0.nick
            boolean r3 = android.text.TextUtils.equals(r5, r3)
            if (r3 != 0) goto L_0x002e
            java.lang.String r3 = r0.email
            boolean r3 = android.text.TextUtils.equals(r5, r3)
            if (r3 != 0) goto L_0x002e
            java.lang.String r3 = r0.mobile
            boolean r3 = android.text.TextUtils.equals(r5, r3)
            if (r3 == 0) goto L_0x000a
        L_0x002e:
            return r0
        L_0x002f:
            r0 = 0
            goto L_0x002e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ali.user.open.history.AccountHistoryManager.matchHistoryAccount(java.lang.String):com.ali.user.open.history.HistoryAccount");
    }

    public List<HistoryAccount> getHistoryAccounts() {
        String loginHistoryJson = ((StorageService) AliMemberSDK.getService(StorageService.class)).getDDpExValue(HISTORY_LOGIN_ACCOUNTS);
        if (TextUtils.isEmpty(loginHistoryJson)) {
            return new ArrayList<>();
        }
        try {
            return parseObject(loginHistoryJson);
        } catch (JSONException e) {
            List<HistoryAccount> accountList = new ArrayList<>();
            ((StorageService) AliMemberSDK.getService(StorageService.class)).removeDDpExValue(HISTORY_LOGIN_ACCOUNTS);
            return accountList;
        }
    }

    private List<HistoryAccount> parseObject(String loginJson) throws JSONException {
        List<HistoryAccount> accountList = new ArrayList<>();
        if (TextUtils.isEmpty(loginJson)) {
            return null;
        }
        JSONArray array = new JSONArray(loginJson);
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            if (obj != null) {
                HistoryAccount account = new HistoryAccount();
                account.userId = obj.optString("userId");
                account.tokenKey = obj.optString("tokenKey");
                account.mobile = obj.optString("mobile");
                account.nick = obj.optString(TvTaoSharedPerference.NICK);
                account.email = obj.optString("email");
                account.t = obj.optString("t");
                accountList.add(account);
            }
        }
        return accountList;
    }

    private String toJSONString(List<HistoryAccount> historyAccountList) {
        if (historyAccountList == null || historyAccountList.size() <= 0) {
            return "";
        }
        JSONArray array = new JSONArray();
        for (HistoryAccount account : historyAccountList) {
            JSONObject object = new JSONObject();
            try {
                object.put("userId", account.userId);
                object.put("tokenKey", account.tokenKey);
                object.put(TvTaoSharedPerference.NICK, account.nick);
                object.put("email", account.email);
                object.put("mobile", account.mobile);
                object.put("t", String.valueOf(System.currentTimeMillis()));
                array.put(object);
            } catch (JSONException e) {
                e.printStackTrace();
                return "";
            }
        }
        return array.toString();
    }
}
