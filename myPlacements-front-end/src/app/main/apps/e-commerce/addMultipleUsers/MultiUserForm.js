import React, { useEffect } from "react";
import { FuseChipSelect } from "@fuse";
import _ from "@lodash";
import * as Actions from "../store/actions";
import { useDispatch, useSelector } from "react-redux";
import { useForm } from "@fuse/hooks";
import { Icon, TextField, Button } from "@material-ui/core";

function MultiUserForm() {
  const multiUser = useSelector(({ eCommerceApp }) => eCommerceApp.multiUser);
  const dispatch = useDispatch();
  const defaultFormState = {
    emailList: [],

    roleId: "",
  };
  const { form, handleChange, setForm } = useForm(defaultFormState);

  useEffect(() => {
    if (
      (multiUser.data[0] && !form) ||
      (multiUser.data[0] && form && multiUser.data[0].id !== form.id)
    ) {
      setForm(multiUser.data[0]);
    }
  }, [form, multiUser.data[0], setForm]);

  function canBeSubmitted() {
    return form.emailList.length > 0;
  }

  function handleSubmit(event) {
    event.preventDefault();
    //console.log(form);
    dispatch(Actions.addMultipleUsers(form));
  }

  function handleChipChange(value, name) {
    setForm(
      _.set(
        { ...form },
        name,
        value.map((item) => item.value)
      )
    );
  }

  return (
    <form
      noValidate
      onSubmit={handleSubmit}
      className="lg:justify-center  md:justify-end"
      //className="flex flex-col overflow-hidden"
    >
      <div className="p-16 sm:p-24 max-w-2xl">
        <div className="min-w-48 pt-20">
          <Icon color="action">email</Icon>
        </div>
        <FuseChipSelect
          className="mt-8 mb-24"
          value={form.emailList.map((item) => ({
            value: item,
            label: item,
          }))}
          onChange={(value) => handleChipChange(value, "emailList")}
          placeholder="Add multiple Emails"
          textFieldProps={{
            label: "Emails",
            InputLabelProps: {
              shrink: true,
            },
            variant: "standard",
          }}
          isMulti
        />
      </div>

      <div className="flex items-center max-w-full">
        <div className="min-w-48 pt-20">
          <Icon color="action">work</Icon>
        </div>
        <TextField
          className="mb-24"
          label="Role"
          id="roleId"
          name="roleId"
          value={form.roleId}
          onChange={handleChange}
          variant="standard"
          fullWidth
          required
        />
      </div>
      <Button
        variant="contained"
        color="primary"
        onClick={handleSubmit}
        type="submit"
        disabled={!canBeSubmitted()}
      >
        Add
      </Button>
    </form>
  );
}

export default MultiUserForm;
